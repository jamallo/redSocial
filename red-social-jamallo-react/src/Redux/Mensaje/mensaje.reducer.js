import { CREATE_CHAT_SUCCESS, CREATE_MENSAJE_SUCCESS, GET_ALL_CHATS_SUCCESS } from "./mensaje.actionType";

const initialState = {
    mensajes: [],
    chats: [],
    loading: false,
    error: null,
    mensaje: null
}

 export const mensajeReducer = (state = initialState, action) => {

    switch (action.type) {
        case CREATE_MENSAJE_SUCCESS:
            
            return {...state,mensaje: action.payload}

        case CREATE_CHAT_SUCCESS:

            return {...state, chats: [action.payload,...state.chats]}

        case GET_ALL_CHATS_SUCCESS:

            return {...state, chats: action.payload}
        
    
        default:
            return state;
    }
}