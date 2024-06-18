import { api } from "../../config/api";
import { CREATE_CHAT_FAILURE, CREATE_CHAT_REQUEST, CREATE_CHAT_SUCCESS, CREATE_MENSAJE_FAILURE, CREATE_MENSAJE_REQUEST, CREATE_MENSAJE_SUCCESS, GET_ALL_CHATS_FAILURE, GET_ALL_CHATS_REQUEST, GET_ALL_CHATS_SUCCESS } from "./mensaje.actionType";

export const createMensaje = (mensaje) => async(dispatch) => {
    dispatch({type:CREATE_MENSAJE_REQUEST})

    try {
        const {data} = await api.post(`/api/mensajes`, mensaje);

        console.log("creado mensaje", data);

        dispatch({type: CREATE_MENSAJE_SUCCESS, payload:data});
        
    } catch (error) {

        console.log("Atrapando error ", error);

        dispatch ({
            type: CREATE_MENSAJE_FAILURE, payload:error
        });
        
    }

};

export const createChat = (chat) => async(dispatch) => {
    dispatch({type:CREATE_CHAT_REQUEST})

    try {
        const {data} = await api.post(`/api/chats`, chat);

        console.log("creado chat", data);

        dispatch({type: CREATE_CHAT_SUCCESS, payload:data});
        
    } catch (error) {

        console.log("Atrapando error ", error);

        dispatch ({
            type: CREATE_CHAT_FAILURE, payload:error
        });
        
    }

};

export const getAllChats = (mensaje) => async(dispatch) => {
    dispatch({type:GET_ALL_CHATS_REQUEST})

    try {
        const {data} = await api.get(`/api/chats/user`, mensaje);

        console.log("obteniendo todos los chats", data);

        dispatch({type: GET_ALL_CHATS_SUCCESS, payload:data});
        
    } catch (error) {

        console.log("Atrapando error ", error);

        dispatch ({
            type: GET_ALL_CHATS_FAILURE, payload:error
        });
        
    }

};