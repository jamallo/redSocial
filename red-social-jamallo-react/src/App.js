
import './App.scss';
import Autentificacion from './paginas/Autentificacion/Autentificacion';
import Mensajes from './paginas/Mensajes/Mensajes';
import PaginaPrincipal from './paginas/PaginaPrincipal/PaginaPrincipal';
import Fondo from './paginas/fondo/fondo';
import { Routes, Route } from "react-router-dom";

function App() {
  return (
    <div className="">
      <Fondo/>
      <Routes>
        <Route path='/*' element={<PaginaPrincipal/>}/>
        <Route path='/mensajes' element={<Mensajes/>}/>
        <Route path='/*' element={<Autentificacion/>}/>

      </Routes>
      
    </div>
  );
}

export default App;
