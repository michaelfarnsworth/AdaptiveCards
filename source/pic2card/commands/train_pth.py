"""
Pytorch training pipeline for FASTER-RCNN
"""
# pylint: disable=no-value-for-parameter

import datetime
import click
from detecto.utils import xml_to_csv
from detecto.utils import normalize_transform
from detecto.core import DataLoader, Dataset
import torchvision.transforms as Tv
from torch.utils.tensorboard import SummaryWriter
from mystique.models.pth.frcnn import CustomModel


@click.command()
@click.option(
    "--train-dir",
    help="Path to the labelmg img+xml folder for training",
    required=True,
)
@click.option(
    "--val-dir",
    help="Path to the labelmg img+xml folder for validation",
    required=True,
)
@click.option("--save-dir", help="model save dir", required=True)
@click.option("--epochs", default=10, help="Number of epochs", required=False)
def train_frcnn(train_dir, val_dir, save_dir, epochs):
    """
    Pytorch training pipeline for FASTER-RCNN
    """

    # Create the labels out of the xml files on the fly
    train_labels = xml_to_csv(train_dir, f"{train_dir}/../train_label.csv")
    # val_labels = xml_to_csv(
    #         val_dir,
    #         f"{val_dir}/../test_label.csv"
    # )
    # Image reader and pre-processing pipeline.
    transformer = Tv.Compose(
        [
            Tv.ToPILImage(),
            lambda image: image.convert("RGB"),
            Tv.ToTensor(),
            normalize_transform(),
        ]
    )

    dataset = Dataset(
        f"{train_dir}/../train_label.csv",
        image_folder=train_dir,
        transform=transformer,
    )

    val_dataset = Dataset(
        f"{val_dir}/../test_label.csv",
        image_folder=val_dir,
        transform=transformer,
    )

    train_dataloader = DataLoader(dataset, batch_size=1)
    val_dataloader = DataLoader(val_dataset, batch_size=1)

    classes = train_labels["class"].unique().tolist()

    tb_writer = SummaryWriter("Second")
    new_model = CustomModel(classes, log_writer=tb_writer)

    new_model.fit(
        train_dataloader,
        val_dataset=val_dataloader,
        verbose=True,
        epochs=epochs,
    )

    timestamp = datetime.datetime.now().strftime("%Y-%m-%d-%s")
    model_path = f"{save_dir}/faster-rcnn-{timestamp}-epochs_{epochs}.pth"
    new_model.save(model_path)
    print(f"model saved at: {model_path}")


if __name__ == "__main__":
    train_frcnn()
