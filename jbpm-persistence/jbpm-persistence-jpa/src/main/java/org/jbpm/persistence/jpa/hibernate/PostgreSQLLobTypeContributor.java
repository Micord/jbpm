/*
 * Copyright 2020 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jbpm.persistence.jpa.hibernate;

import org.hibernate.boot.model.TypeContributions;
import org.hibernate.boot.model.TypeContributor;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.jdbc.spi.JdbcServices;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.StandardBasicTypeTemplate;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.descriptor.java.PrimitiveByteArrayJavaType;
import org.hibernate.type.descriptor.jdbc.BinaryJdbcType;


public class PostgreSQLLobTypeContributor implements TypeContributor {
    private static final boolean useBytea;

    static {
        String useByteaStr = System.getenv("ORG_KIE_PERSISTENCE_POSTGRESQL_USEBYTEA");

        if (useByteaStr == null) {
            useByteaStr = System.getProperty("org.kie.persistence.postgresql.useBytea", "false");
        }
        useBytea = Boolean.parseBoolean(useByteaStr);
    }

    public class ByteaContributorType extends StandardBasicTypeTemplate<byte[]> {

        private static final long serialVersionUID = 1619875355308645967L;

        public ByteaContributorType() {
            super(BinaryJdbcType.INSTANCE, PrimitiveByteArrayJavaType.INSTANCE, StandardBasicTypes.MATERIALIZED_BLOB.getName());
        }

    }

    @Override
    public void contribute(TypeContributions typeContributions, ServiceRegistry serviceRegistry) {
        final Dialect dialect = serviceRegistry.getService(JdbcServices.class).getDialect();
        if (dialect instanceof org.hibernate.dialect.PostgreSQLDialect) {

            if (useBytea) {
                typeContributions.contributeType(new ByteaContributorType());
            }
        }
    }
}
