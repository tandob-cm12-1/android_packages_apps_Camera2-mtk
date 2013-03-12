/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.gallery3d.filtershow.filters;

import com.android.gallery3d.app.Log;
import com.android.gallery3d.filtershow.controller.Control;
import com.android.gallery3d.filtershow.controller.ParameterInteger;

public class FilterBasicRepresentation extends FilterRepresentation implements ParameterInteger {
    private static final String LOGTAG = "FilterBasicRepresentation";
    private int mMinimum;
    private int mValue;
    private int mMaximum;
    private int mDefaultValue;
    private int mPreviewValue;

    public FilterBasicRepresentation(String name, int minimum, int value, int maximum) {
        super(name);
        mMinimum = minimum;
        mMaximum = maximum;
        setValue(value);
    }

    @Override
    public String toString() {
        return getName() + " : " + mMinimum + " < " + mValue + " < " + mMaximum;
    }

    @Override
    public FilterRepresentation clone() throws CloneNotSupportedException {
        FilterBasicRepresentation representation = (FilterBasicRepresentation) super.clone();
        representation.setMinimum(getMinimum());
        representation.setMaximum(getMaximum());
        representation.setValue(getValue());
        Log.v(LOGTAG, "cloning from <" + this + "> to <" + representation + ">");
        return representation;
    }

    @Override
    public void useParametersFrom(FilterRepresentation a) {
        if (a instanceof FilterBasicRepresentation) {
            FilterBasicRepresentation representation = (FilterBasicRepresentation) a;
            setMinimum(representation.getMinimum());
            setMaximum(representation.getMaximum());
            setValue(representation.getValue());
            setDefaultValue(representation.getDefaultValue());
            setPreviewValue(representation.getPreviewValue());
        }
    }

    @Override
    public boolean equals(FilterRepresentation representation) {
        if (!super.equals(representation)) {
            return false;
        }
        if (representation instanceof FilterBasicRepresentation) {
            FilterBasicRepresentation basic = (FilterBasicRepresentation) representation;
            if (basic.mMinimum == mMinimum
                    && basic.mMaximum == mMaximum
                    && basic.mValue == mValue
                    && basic.mDefaultValue == mDefaultValue
                    && basic.mPreviewValue == mPreviewValue) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int getMinimum() {
        return mMinimum;
    }

    public void setMinimum(int minimum) {
        mMinimum = minimum;
    }

    @Override
    public int getValue() {
        return mValue;
    }

    @Override
    public void setValue(int value) {
        mValue = value;
        if (mValue < mMinimum) {
            mValue = mMinimum;
        }
        if (mValue > mMaximum) {
            mValue = mMaximum;
        }
    }

    @Override
    public int getMaximum() {
        return mMaximum;
    }

    public void setMaximum(int maximum) {
        mMaximum = maximum;
    }

    public void setDefaultValue(int defaultValue) {
        mDefaultValue = defaultValue;
    }

    @Override
    public int getDefaultValue() {
        return mDefaultValue;
    }

    public int getPreviewValue() {
        return mPreviewValue;
    }

    public void setPreviewValue(int previewValue) {
        mPreviewValue = previewValue;
    }

    @Override
    public String getStateRepresentation() {
        return "" + getValue();
    }

    @Override
    public String getParameterType(){
        return sParameterType;
    }

    @Override
    public void setController(Control control) {
    }

    @Override
    public String getValueString() {
        return getStateRepresentation();
    }

    @Override
    public String getParameterName() {
        return getName();
    }
}
