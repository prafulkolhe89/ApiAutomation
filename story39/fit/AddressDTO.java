/*******************************************************************************
 * Copyright (c) 2018 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package com.ibm.silverpop.sms.smpp.story39.fit;

import java.io.Serializable;

public class AddressDTO implements Serializable {
    private static final long serialVersionUID = 5881287026977988348L;

    private Integer ton;
    private Integer npi;
    private String address;

    public Integer getTon() {
        return ton;
    }
    public void setTon(Integer ton) {
        this.ton = ton;
    }
    public Integer getNpi() {
        return npi;
    }
    public void setNpi(Integer npi) {
        this.npi = npi;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}