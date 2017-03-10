package com.mich.android.mich.transport.requests;

public class SearchRequest extends BaseAuthorizedRequest{
    String term;

    public SearchRequest(String term) {
        this.term = term;
    }
}