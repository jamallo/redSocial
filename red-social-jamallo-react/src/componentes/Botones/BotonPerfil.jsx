import React from 'react';
import PropTypes from 'prop-types';
import '../../styles/BotonMas.scss';

const BotonMas = ({ src, alt, children }) => {
  return (
    <div className="avatar">
      {src ? <img src={src} alt={alt} /> : children}
    </div>
  );
};
BotonMas.propTypes = {
    src: PropTypes.string,
    alt: PropTypes.string,
    children: PropTypes.node,
}
export default BotonMas;
