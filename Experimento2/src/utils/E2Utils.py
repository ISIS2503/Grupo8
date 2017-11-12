import mimetypes
import smtplib
from email.mime.base import MIMEBase
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText

import sys

smtp_client = 'smtp.office365.com'
sender = 'e-mail'
password = 'password'


def sendTo(to, attachmentPath, subject, msg_body):
    try:
        msg = MIMEMultipart()
        msg['Subject'] = subject
        msg['From'] = sender
        msg['To'] = " ,".join(to)

        print('From:', sender, "\nTo: ", to, "\nAsunto:", subject, '\nBody:', msg_body, '\nAttachment:', attachmentPath)

        msg.attach(MIMEText(msg_body, 'plain'))
        if attachmentPath:
            ctype, encoding = mimetypes.guess_type(attachmentPath)
            if ctype is None or encoding is not None:
                ctype = "application/octet-stream"
            maintype, subtype = ctype.split("/", 1)

            fp = open(attachmentPath, "rb")
            attachment = MIMEBase(maintype, subtype)
            attachment.set_payload(fp.read())
            fp.close()

            attachment.add_header("Content-Disposition", "attachment")
            msg.attach(attachment)

        smtp = smtplib.SMTP(smtp_client)
        smtp.starttls()
        smtp.login(sender, password)
        smtp.send_message(msg)
        smtp.quit()
    except:
        print('No se pudo enviar el correo', file=sys.stderr)
