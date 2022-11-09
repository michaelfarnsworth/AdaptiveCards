// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
#include "pch.h"

#include "AdaptiveTextConfig.h"

using namespace Microsoft::WRL;
using namespace ABI::AdaptiveCards::Rendering::Uwp;

namespace AdaptiveCards::Rendering::Uwp
{
    HRESULT AdaptiveTextConfig::RuntimeClassInitialize() noexcept
    try
    {
        TextConfig textConfig;
        return RuntimeClassInitialize(textConfig);
    }
    CATCH_RETURN;

    HRESULT AdaptiveTextConfig::RuntimeClassInitialize(TextConfig textConfig) noexcept
    {
        m_textWeight = static_cast<ABI::AdaptiveCards::Rendering::Uwp::TextWeight>(textConfig.weight);
        m_textSize = static_cast<ABI::AdaptiveCards::Rendering::Uwp::TextSize>(textConfig.size);
        m_textColor = static_cast<ABI::AdaptiveCards::Rendering::Uwp::ForegroundColor>(textConfig.color);
        m_isSubtle = textConfig.isSubtle;
        m_wrap = textConfig.wrap;
        m_maxWidth = textConfig.maxWidth;

        return S_OK;
    }

    HRESULT AdaptiveTextConfig::get_Size(_Out_ ABI::AdaptiveCards::Rendering::Uwp::TextSize* textSize)
    {
        *textSize = m_textSize;
        return S_OK;
    }

    HRESULT AdaptiveTextConfig::put_Size(ABI::AdaptiveCards::Rendering::Uwp::TextSize textSize)
    {
        m_textSize = textSize;
        return S_OK;
    }

    HRESULT AdaptiveTextConfig::get_Weight(_Out_ ABI::AdaptiveCards::Rendering::Uwp::TextWeight* textWeight)
    {
        *textWeight = m_textWeight;
        return S_OK;
    }

    HRESULT AdaptiveTextConfig::put_Weight(ABI::AdaptiveCards::Rendering::Uwp::TextWeight textWeight)
    {
        m_textWeight = textWeight;
        return S_OK;
    }

    HRESULT AdaptiveTextConfig::get_Color(_Out_ ABI::AdaptiveCards::Rendering::Uwp::ForegroundColor* textColor)
    {
        *textColor = m_textColor;
        return S_OK;
    }

    HRESULT AdaptiveTextConfig::put_Color(ABI::AdaptiveCards::Rendering::Uwp::ForegroundColor textColor)
    {
        m_textColor = textColor;
        return S_OK;
    }

    HRESULT AdaptiveTextConfig::get_IsSubtle(_Out_ boolean* isSubtle)
    {
        *isSubtle = m_isSubtle;
        return S_OK;
    }

    HRESULT AdaptiveTextConfig::put_IsSubtle(boolean isSubtle)
    {
        m_isSubtle = isSubtle;
        return S_OK;
    }

    HRESULT AdaptiveTextConfig::get_Wrap(_Out_ boolean* wrap)
    {
        *wrap = m_wrap;
        return S_OK;
    }
    HRESULT AdaptiveTextConfig::put_Wrap(boolean wrap)
    {
        m_wrap = wrap;
        return S_OK;
    }
    HRESULT AdaptiveTextConfig::get_MaxWidth(_Out_ UINT32* maxWidth)
    {
        *maxWidth = m_maxWidth;
        return S_OK;
    }
    HRESULT AdaptiveTextConfig::put_MaxWidth(UINT32 maxWidth)
    {
        m_maxWidth = maxWidth;
        return S_OK;
    }
}
