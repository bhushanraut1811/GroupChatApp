package br.apps.groupchatapp.service.api;

import java.util.List;

import br.apps.groupchatapp.service.api.model.Conversation;
import br.apps.groupchatapp.service.api.model.Message;
import br.apps.groupchatapp.util.Constant;
import retrofit2.Call;
import retrofit2.http.GET;

public interface IChatAPI {

    /**
     * Provides all chat conversation
     *
     * @return object of ResponseBody
     */
    @GET(Constant.BASE_URL)
    Call<Conversation> getChatConversation();

}
