package com.mich.android.mich.transport.requests;

/**
 * Created by sabagogolidze on 3/10/17.
 */

public class AcceptBattleRequest extends BaseAuthorizedRequest {
    int battleID;

    public AcceptBattleRequest(int battleID) {
        this.battleID = battleID;
    }
}
