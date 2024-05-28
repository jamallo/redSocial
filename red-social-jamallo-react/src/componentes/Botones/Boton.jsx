import React from 'react'

const Boton = (props) => {
    const {text} = props;
    const {onClick} = props;

  return (
    <>
        <button  
            onClick={onClick}
            className='boton'>
            {text}
        </button>
    </>
  )
}

export default Boton
