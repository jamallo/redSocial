
const AWS = require('aws-sdk');

AWS.config.update({ region: 'eu-west-3' });

const s3 = new AWS.S3();

s3.listBuckets((err, data) => {
  if (err) console.log(err, err.stack);
  else console.log(data);
});

export const cargarANube = async (file) => {
  const params = {
    Bucket: 'jamallo',
    Key: `images/${file.name}`,
    Body: file,
    ACL: 'public-read',
  };

  try {
    const { Location } = await s3.upload(params).promise();
    return Location;
  } catch (error) {
    console.error("Error al subir archivo a S3", error);
    return null;
  }
};
