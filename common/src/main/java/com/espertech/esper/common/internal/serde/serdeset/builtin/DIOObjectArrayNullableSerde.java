/*
 ***************************************************************************************
 *  Copyright (C) 2006 EsperTech, Inc. All rights reserved.                            *
 *  http://www.espertech.com/esper                                                     *
 *  http://www.espertech.com                                                           *
 *  ---------------------------------------------------------------------------------- *
 *  The software in this package is published under the terms of the GPL license       *
 *  a copy of which has been included with this distribution in the license.txt file.  *
 ***************************************************************************************
 */
package com.espertech.esper.common.internal.serde.serdeset.builtin;

import com.espertech.esper.common.client.serde.DataInputOutputSerde;
import com.espertech.esper.common.client.serde.EventBeanCollatedWriter;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.lang.reflect.Array;

public class DIOObjectArrayNullableSerde implements DataInputOutputSerde<Object[]> {
    private final Class componentType;
    private final DataInputOutputSerde componentBinding;

    public DIOObjectArrayNullableSerde(Class componentType, DataInputOutputSerde componentBinding) {
        this.componentType = componentType;
        this.componentBinding = componentBinding;
    }

    public void write(Object[] object, DataOutput output) throws IOException {
        writeInternal(object, output);
    }

    public Object[] read(DataInput input) throws IOException {
        return readInternal(input);
    }

    public void write(Object[] object, DataOutput output, byte[] unitKey, EventBeanCollatedWriter writer) throws IOException {
        writeInternal(object, output);
    }

    public Object[] read(DataInput input, byte[] unitKey) throws IOException {
        return readInternal(input);
    }

    private void writeInternal(Object[] object, DataOutput output) throws IOException {
        if (object == null) {
            output.writeInt(-1);
            return;
        }
        output.writeInt(object.length);
        for (Object i : object) {
            componentBinding.write(i, output, null, null);
        }
    }

    private Object[] readInternal(DataInput input) throws IOException {
        int len = input.readInt();
        if (len == -1) {
            return null;
        }
        Object array = Array.newInstance(componentType, len);
        for (int i = 0; i < len; i++) {
            Array.set(array, i, componentBinding.read(input, null));
        }
        return (Object[]) array;
    }
}
