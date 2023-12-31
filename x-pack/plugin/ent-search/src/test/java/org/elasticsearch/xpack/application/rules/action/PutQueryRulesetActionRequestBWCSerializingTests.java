/*
 * Copyright Elasticsearch B.V. and/or licensed to Elasticsearch B.V. under one
 * or more contributor license agreements. Licensed under the Elastic License
 * 2.0; you may not use this file except in compliance with the Elastic License
 * 2.0.
 */

package org.elasticsearch.xpack.application.rules.action;

import org.elasticsearch.TransportVersion;
import org.elasticsearch.common.io.stream.Writeable;
import org.elasticsearch.xcontent.XContentParser;
import org.elasticsearch.xpack.application.rules.QueryRuleset;
import org.elasticsearch.xpack.application.search.SearchApplicationTestUtils;
import org.elasticsearch.xpack.core.ml.AbstractBWCSerializationTestCase;

import java.io.IOException;

public class PutQueryRulesetActionRequestBWCSerializingTests extends AbstractBWCSerializationTestCase<PutQueryRulesetAction.Request> {

    private QueryRuleset queryRulesSet;

    @Override
    protected Writeable.Reader<PutQueryRulesetAction.Request> instanceReader() {
        return PutQueryRulesetAction.Request::new;
    }

    @Override
    protected PutQueryRulesetAction.Request createTestInstance() {
        this.queryRulesSet = SearchApplicationTestUtils.randomQueryRuleset();
        return new PutQueryRulesetAction.Request(this.queryRulesSet);
    }

    @Override
    protected PutQueryRulesetAction.Request mutateInstance(PutQueryRulesetAction.Request instance) {
        return randomValueOtherThan(instance, this::createTestInstance);
    }

    @Override
    protected PutQueryRulesetAction.Request doParseInstance(XContentParser parser) throws IOException {
        return PutQueryRulesetAction.Request.fromXContent(this.queryRulesSet.id(), parser);
    }

    @Override
    protected PutQueryRulesetAction.Request mutateInstanceForVersion(PutQueryRulesetAction.Request instance, TransportVersion version) {
        return new PutQueryRulesetAction.Request(instance.queryRuleset());
    }
}
