/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 4.0.2
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package io.adaptivecards.objectmodel;

public class NumberInput extends BaseInputElement {
  private transient long swigCPtr;
  private transient boolean swigCMemOwnDerived;

  protected NumberInput(long cPtr, boolean cMemoryOwn) {
    super(AdaptiveCardObjectModelJNI.NumberInput_SWIGSmartPtrUpcast(cPtr), true);
    swigCMemOwnDerived = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(NumberInput obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void swigSetCMemOwn(boolean own) {
    swigCMemOwnDerived = own;
    super.swigSetCMemOwn(own);
  }

  @SuppressWarnings("deprecation")
  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwnDerived) {
        swigCMemOwnDerived = false;
        AdaptiveCardObjectModelJNI.delete_NumberInput(swigCPtr);
      }
      swigCPtr = 0;
    }
    super.delete();
  }

  public NumberInput() {
    this(AdaptiveCardObjectModelJNI.new_NumberInput__SWIG_0(), true);
  }

  public NumberInput(NumberInput arg0) {
    this(AdaptiveCardObjectModelJNI.new_NumberInput__SWIG_1(NumberInput.getCPtr(arg0), arg0), true);
  }

  public JsonValue SerializeToJsonValue() {
    return new JsonValue(AdaptiveCardObjectModelJNI.NumberInput_SerializeToJsonValue(swigCPtr, this), true);
  }

  public String GetPlaceholder() {
    return AdaptiveCardObjectModelJNI.NumberInput_GetPlaceholder(swigCPtr, this);
  }

  public void SetPlaceholder(String value) {
    AdaptiveCardObjectModelJNI.NumberInput_SetPlaceholder(swigCPtr, this, value);
  }

  public @androidx.annotation.Nullable Double GetValue() {
  StdOptionalDouble optvalue = new StdOptionalDouble(AdaptiveCardObjectModelJNI.NumberInput_GetValue(swigCPtr, this), false);
  return optvalue.has_value() ? optvalue.value() : null;
}

  public void SetValue(@androidx.annotation.Nullable Double value) {
    StdOptionalDouble optvalue = (value == null) ? new StdOptionalDouble() : new StdOptionalDouble(value);
    {
      AdaptiveCardObjectModelJNI.NumberInput_SetValue(swigCPtr, this, StdOptionalDouble.getCPtr(optvalue), optvalue);
    }
  }

  public @androidx.annotation.Nullable Double GetMax() {
  StdOptionalDouble optvalue = new StdOptionalDouble(AdaptiveCardObjectModelJNI.NumberInput_GetMax(swigCPtr, this), false);
  return optvalue.has_value() ? optvalue.value() : null;
}

  public void SetMax(@androidx.annotation.Nullable Double value) {
    StdOptionalDouble optvalue = (value == null) ? new StdOptionalDouble() : new StdOptionalDouble(value);
    {
      AdaptiveCardObjectModelJNI.NumberInput_SetMax(swigCPtr, this, StdOptionalDouble.getCPtr(optvalue), optvalue);
    }
  }

  public @androidx.annotation.Nullable Double GetMin() {
  StdOptionalDouble optvalue = new StdOptionalDouble(AdaptiveCardObjectModelJNI.NumberInput_GetMin(swigCPtr, this), false);
  return optvalue.has_value() ? optvalue.value() : null;
}

  public void SetMin(@androidx.annotation.Nullable Double value) {
    StdOptionalDouble optvalue = (value == null) ? new StdOptionalDouble() : new StdOptionalDouble(value);
    {
      AdaptiveCardObjectModelJNI.NumberInput_SetMin(swigCPtr, this, StdOptionalDouble.getCPtr(optvalue), optvalue);
    }
  }

  public static NumberInput dynamic_cast(BaseCardElement baseCardElement) {
    long cPtr = AdaptiveCardObjectModelJNI.NumberInput_dynamic_cast(BaseCardElement.getCPtr(baseCardElement), baseCardElement);
    return (cPtr == 0) ? null : new NumberInput(cPtr, true);
  }

}
