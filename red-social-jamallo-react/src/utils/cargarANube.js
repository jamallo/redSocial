import AWS from 'aws-sdk';

const s3 = new AWS.S3({
  accessKeyId: '905418119496',
  secretAccessKey: '',
  region: 'eu-west-3',
});

export const cargarANube = async (file) => {
  const params = {
    Bucket: 'YOUR_BUCKET_NAME',
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
