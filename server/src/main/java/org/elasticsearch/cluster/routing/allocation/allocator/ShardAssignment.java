/*
 * Copyright Elasticsearch B.V. and/or licensed to Elasticsearch B.V. under one
 * or more contributor license agreements. Licensed under the Elastic License
 * 2.0 and the Server Side Public License, v 1; you may not use this file except
 * in compliance with, at your election, the Elastic License 2.0 or the Server
 * Side Public License, v 1.
 */

package org.elasticsearch.cluster.routing.allocation.allocator;

import org.elasticsearch.cluster.routing.ShardRouting;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toUnmodifiableSet;

public record ShardAssignment(Set<String> nodeIds, int unassigned, int ignored) {

    public boolean isIgnored() {
        return ignored > 0 && nodeIds.isEmpty();
    }

    public static ShardAssignment of(List<ShardRouting> routings) {
        return new ShardAssignment(routings.stream().map(ShardRouting::currentNodeId).collect(toUnmodifiableSet()), 0, 0);
    }
}