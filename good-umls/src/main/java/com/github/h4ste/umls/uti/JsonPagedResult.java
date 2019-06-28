package com.github.h4ste.umls.uti;

public class JsonPagedResult<T> {
  private final int pageSize;
  private final int pageNumber;
  private final T result;

  public JsonPagedResult(int pageSize, int pageNumber, T result) {
    this.pageSize = pageSize;
    this.pageNumber = pageNumber;
    this.result = result;
  }

  public int getPageSize() {
    return pageSize;
  }

  public int getPageNumber() {
    return pageNumber;
  }

  public T getResult() {
    return result;
  }

  boolean isLastPage() {
    if (result instanceof JsonSearchResults) {
      final JsonSearchResults searchResults = (JsonSearchResults) result;
      if (searchResults.size() == 1 && searchResults.get(0) instanceof JsonBaseSearchResult) {
        final JsonBaseSearchResult result = (JsonBaseSearchResult) searchResults.get(0);
        assert "NONE".equals(result.getUi());
        assert "NO RESULTS".equals(result.getName());
        return true;
      }
      return false;
    } else {
      return true;
    }
  }
}
