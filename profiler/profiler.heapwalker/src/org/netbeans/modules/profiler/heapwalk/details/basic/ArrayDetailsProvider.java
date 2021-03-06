/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.netbeans.modules.profiler.heapwalk.details.basic;

import org.netbeans.lib.profiler.heap.Heap;
import org.netbeans.lib.profiler.heap.Instance;
import org.netbeans.lib.profiler.heap.ObjectArrayInstance;
import org.netbeans.lib.profiler.heap.PrimitiveArrayInstance;
import org.netbeans.modules.profiler.heapwalk.details.spi.DetailsProvider;
import org.netbeans.modules.profiler.heapwalk.details.spi.DetailsUtils;
import org.openide.util.NbBundle;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Jiri Sedlacek
 */
@NbBundle.Messages({
    "ArrayDetailsProvider_OneItemString=1 item",                                // NOI18N
    "ArrayDetailsProvider_ItemsNumberString={0} items"                          // NOI18N
})
@ServiceProvider(service=DetailsProvider.class)
public final class ArrayDetailsProvider extends DetailsProvider {
    
    public String getDetailsString(String className, Instance instance, Heap heap) {
        if (instance instanceof PrimitiveArrayInstance) {
            if ("char[]".equals(instance.getJavaClass().getName())) {           // NOI18N
                return DetailsUtils.getPrimitiveArrayString(
                        instance, 0, -1, null, "...");                          // NOI18N
            } else {
                return getItemsString(((PrimitiveArrayInstance)instance).getLength());
            }
        } else if (instance instanceof ObjectArrayInstance) {
            return getItemsString(((ObjectArrayInstance)instance).getLength());
        }
        return null;
    }
    
    public View getDetailsView(String className, Instance instance, Heap heap) {
        if (instance instanceof PrimitiveArrayInstance)
            return new ArrayValueView(className, instance, heap);
        else return null;
    }
    
    private static String getItemsString(int length) {
        return length == 1 ? Bundle.ArrayDetailsProvider_OneItemString() :
                             Bundle.ArrayDetailsProvider_ItemsNumberString(length);
    }
    
}
