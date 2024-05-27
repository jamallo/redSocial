
import { useDispatch, useSelector } from 'react-redux';
import './App.scss';
import Autentificacion from './paginas/Autentificacion/Autentificacion';
import Mensajes from './paginas/Mensajes/Mensajes';
import PaginaPrincipal from './paginas/PaginaPrincipal/PaginaPrincipal';
import Fondo from './paginas/fondo/fondo';
import { Routes, Route } from "react-router-dom";
import { useEffect } from 'react';
import { getProfileAction } from './Redux/Auth/auth.action';

function App() {
  const {auth} = useSelector(store=>store);
  const dispatch=useDispatch();
  const jwt = localStorage.getItem("jwt");

  useEffect(() => {
    dispatch(getProfileAction(jwt))
  },[jwt])

  return (
    <div className="">
      <Fondo/>
      <Routes>
        <Route path='/*' element={auth.user?<PaginaPrincipal/>:<Autentificacion/>}/>
        <Route path='/mensajes' element={<Mensajes/>}/>
        <Route path='/*' element={<Autentificacion/>}/>

      </Routes>
      
    </div>
  );
}

export default App;
