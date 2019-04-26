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
package com.espertech.esper.common.internal.epl.resultset.select.core;

import com.espertech.esper.common.internal.compile.stage3.StmtClassForgeableFactory;

import java.util.List;

public class SelectExprProcessorDescriptor {
    private final SelectSubscriberDescriptor subscriberDescriptor;
    private final SelectExprProcessorForge forge;
    private final List<StmtClassForgeableFactory> additionalForgeables;

    public SelectExprProcessorDescriptor(SelectSubscriberDescriptor subscriberDescriptor, SelectExprProcessorForge forge, List<StmtClassForgeableFactory> additionalForgeables) {
        this.subscriberDescriptor = subscriberDescriptor;
        this.forge = forge;
        this.additionalForgeables = additionalForgeables;
    }

    public SelectSubscriberDescriptor getSubscriberDescriptor() {
        return subscriberDescriptor;
    }

    public SelectExprProcessorForge getForge() {
        return forge;
    }

    public List<StmtClassForgeableFactory> getAdditionalForgeables() {
        return additionalForgeables;
    }
}
