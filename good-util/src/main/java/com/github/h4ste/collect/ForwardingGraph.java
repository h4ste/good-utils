package com.github.h4ste.collect;

import com.google.common.annotations.Beta;
import com.google.common.collect.ForwardingObject;
import com.google.common.graph.ElementOrder;
import com.google.common.graph.EndpointPair;
import com.google.common.graph.Graph;
import java.util.Set;

@Beta
public abstract class ForwardingGraph<N> extends ForwardingObject implements Graph<N> {

  @Override
  abstract protected Graph<N> delegate();

  @Override
  public Set<N> nodes() {
    return delegate().nodes();
  }

  @Override
  public Set<EndpointPair<N>> edges() {
    return delegate().edges();
  }

  @Override
  public boolean isDirected() {
    return delegate().isDirected();
  }

  @Override
  public boolean allowsSelfLoops() {
    return delegate().allowsSelfLoops();
  }

  @Override
  public ElementOrder<N> nodeOrder() {
    return delegate().nodeOrder();
  }

  @Override
  public Set<N> adjacentNodes(N node) {
    return delegate().adjacentNodes(node);
  }

  @Override
  public Set<N> predecessors(N node) {
    return delegate().predecessors(node);
  }

  @Override
  public Set<N> successors(N node) {
    return delegate().successors(node);
  }

  @Override
  public Set<EndpointPair<N>> incidentEdges(N node) {
    return delegate().incidentEdges(node);
  }

  @Override
  public int degree(N node) {
    return delegate().degree(node);
  }

  @Override
  public int inDegree(N node) {
    return delegate().inDegree(node);
  }

  @Override
  public int outDegree(N node) {
    return delegate().outDegree(node);
  }

  @Override
  public boolean hasEdgeConnecting(N nodeU, N nodeV) {
    return delegate().hasEdgeConnecting(nodeU, nodeV);
  }
}
