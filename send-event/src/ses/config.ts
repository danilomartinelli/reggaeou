import AWS from "aws-sdk";

export async function sendEmail(to: string[], subject: string, data: string) {
  AWS.config.update({ region: "us-west-2" });

  const params = {
    Destination: {
      ToAddresses: to
    },
    Message: {
      Body: {
        Text: {
          Charset: "UTF-8",
          Data: data
        }
      },
      Subject: {
        Charset: "UTF-8",
        Data: subject
      }
    },
    Source: "SENDER_EMAIL_ADDRESS"
  };

  const sendData = await new AWS.SES({ apiVersion: "2010-12-01" })
    .sendEmail(params)
    .promise();

  return sendData.MessageId;
}
