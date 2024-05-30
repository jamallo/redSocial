import React from 'react'

const Boton = (props) => {
    const {text} = props;
    const {onClick} = props;
    const {type} = props;

  return (
    <>
        <button  
            type={type}
            onClick={onClick}
            className='boton'>
            {text}
        </button>
    </>
  )
}

export default Boton
