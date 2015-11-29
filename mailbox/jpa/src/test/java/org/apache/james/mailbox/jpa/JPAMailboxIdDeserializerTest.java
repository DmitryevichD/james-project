/****************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one   *
 * or more contributor license agreements.  See the NOTICE file *
 * distributed with this work for additional information        *
 * regarding copyright ownership.  The ASF licenses this file   *
 * to you under the Apache License, Version 2.0 (the            *
 * "License"); you may not use this file except in compliance   *
 * with the License.  You may obtain a copy of the License at   *
 *                                                              *
 *   http://www.apache.org/licenses/LICENSE-2.0                 *
 *                                                              *
 * Unless required by applicable law or agreed to in writing,   *
 * software distributed under the License is distributed on an  *
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY       *
 * KIND, either express or implied.  See the License for the    *
 * specific language governing permissions and limitations      *
 * under the License.                                           *
 ****************************************************************/

package org.apache.james.mailbox.jpa;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.james.mailbox.store.mail.model.MailboxIdDeserialisationException;
import org.junit.Before;
import org.junit.Test;

public class JPAMailboxIdDeserializerTest {

    private static final String SERIALIZED_ID = "123456789012";
    private static final String MALFORMED_SERIALIZED_ID = "abcz";
    private static final JPAId JPA_ID = JPAId.of(Long.valueOf(SERIALIZED_ID));

    private JPAMailboxIdDeserializer mailboxIdDeserializer;

    @Before
    public void setUp() {
        mailboxIdDeserializer = new JPAMailboxIdDeserializer();
    }

    @Test
    public void deserializeShouldWork() throws Exception {
        assertThat(mailboxIdDeserializer.deserialize(SERIALIZED_ID)).isEqualTo(JPA_ID);
    }

    @Test(expected = MailboxIdDeserialisationException.class)
    public void deserializeShouldThrowOnMalformedData() throws Exception {
        mailboxIdDeserializer.deserialize(MALFORMED_SERIALIZED_ID);
    }

}