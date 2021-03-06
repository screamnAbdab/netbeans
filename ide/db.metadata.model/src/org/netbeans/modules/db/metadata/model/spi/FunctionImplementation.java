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
package org.netbeans.modules.db.metadata.model.spi;

import java.util.Collection;
import org.netbeans.modules.db.metadata.model.MetadataAccessor;
import org.netbeans.modules.db.metadata.model.api.Column;
import org.netbeans.modules.db.metadata.model.api.Function;
import org.netbeans.modules.db.metadata.model.api.Parameter;
import org.netbeans.modules.db.metadata.model.api.Schema;
import org.netbeans.modules.db.metadata.model.api.Value;

/**
 *
 * @author Andrei Badea, Matthias42
 * @since db.metadata.model/1.0
 */
public abstract class FunctionImplementation {

    private Function table;

    public final Function getFunction() {
        if (table == null) {
            table = MetadataAccessor.getDefault().createFunction(this);
        }
        return table;
    }

    public abstract Schema getParent();

    public abstract String getName();

    public abstract Collection<Column> getColumns();

    public abstract Column getColumn(String name);

    public abstract Collection<Parameter> getParameters();

    public abstract Parameter getParameter(String name);

    public abstract Value getReturnValue();

    public abstract void refresh();
}
