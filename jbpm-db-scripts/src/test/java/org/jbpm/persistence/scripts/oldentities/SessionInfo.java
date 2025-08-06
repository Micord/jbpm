/*
 * Copyright 2017 Red Hat, Inc. and/or its affiliates.
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

package org.jbpm.persistence.scripts.oldentities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Version;


@Entity
@SequenceGenerator(name = "sessionInfoIdSeq", sequenceName = "SESSIONINFO_ID_SEQ", allocationSize = 1)
public class SessionInfo {

    private
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "sessionInfoIdSeq")
    Integer id;

    @Version
    @Column(name = "OPTLOCK")
    private int version;

    private Date startDate;
    private Date lastModificationDate;

    @Lob
    @Column(length = 2147483647)
    private byte[] rulesByteArray;

    public SessionInfo() {
        this.startDate = new Date();
    }

    public Integer getId() {
        return this.id;
    }

    public int getVersion() {
        return this.version;
    }

    public void setData(byte[] data) {
        this.rulesByteArray = data;
    }

    public byte[] getData() {
        return this.rulesByteArray;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public Date getLastModificationDate() {
        return this.lastModificationDate;
    }

    public void setLastModificationDate(Date date) {
        this.lastModificationDate = date;
    }

    public void setId(Integer ksessionId) {
        this.id = ksessionId;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public byte[] getRulesByteArray() {
        return rulesByteArray;
    }
}
