package com.ukurirwanda.test;

import com.sendgrid.*;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.ukurirwanda.common.PassCode;
import com.ukurirwanda.dao.CellDao;
import com.ukurirwanda.dao.HibernateUtil;
import com.ukurirwanda.dao.OwnerDao;
import com.ukurirwanda.dao.ProductImageDao;
import com.ukurirwanda.dao.SetupDao;
import com.ukurirwanda.dao.UserDao;
import com.ukurirwanda.domain.Cell;
import com.ukurirwanda.domain.Owner;
import com.ukurirwanda.domain.ProductImage;
import com.ukurirwanda.domain.Setup;
import com.ukurirwanda.domain.User;

public class Main {

    /**
     * @param args the command line arguments
     */
//    private static final String HOST = "smtp.gmail.com";
//    private static final int PORT = 465;
//    private static final boolean SSL_FLAG = true;
    public static void main(String[] args) throws Exception {
//        Main sender = new Main();
//        sender.sendSimpleEmail();

//        Cell c = new CellDao().findOne(Cell.class, "91d99502-40e4-4098-bd33-ab98fd1bb62a");
//        new CellDao().delete(c);
//                Setup s = new Setup();
//                s.setName("Rwanda Truth");
//                s.setAddress("Kigali - Rwanda");
//                s.setPhone("+250256999");
//                s.setEmail("rwandatruth@gmail.com");
//                new SetupDao().create(s);
//        
//                Owner o = new Owner();
//                o.setFirstName("Peter");
//                o.setLastName("UWINEZA");
//                o.setAddress("Kicukiro");
//                o.setEmail("peter@gmail.com");
//                o.setPhone("+250788256999");
//                new OwnerDao().create(o);
//                User u = new User();
//                u.setAccess("Owner");
//                u.setOwner(o);
//                u.setStatus("Active");
//                u.setUsername("peter");
//                u.setPassword(new PassCode().encrypt("peter123"));
//                new UserDao().create(u);
        //        for(ProductImage p: new ProductImageDao().findAll(ProductImage.class)){
        //            p.setStatus("Approved");
        //            new ProductImageDao().update(p);
        //        }
        //        ProductImage p = new ProductImageDao().findOne(ProductImage.class, "02a08e3e-5c62-4c76-aae4-abb39144e9a1");
        //        p.setStatus("Approved");
        //        new ProductImageDao().update(p);
//    private void sendSimpleEmail() {
//
//        String userName = "hbjado5@gmail.com";
//        String password = "nyundosecret";
//
//        String fromAddress = "hbjado5@gmail.com";
//        String toAddress = "mazimpakahassan123@gmail.com";
//        String subject = "Test Mail";
//        String message = "Hello from Apache Mail";
//
//        try {
//            Email email = new SimpleEmail();
//            email.setHostName(HOST);
//            email.setSmtpPort(PORT);
//            email.setAuthenticator(new DefaultAuthenticator(userName, password));
//            email.setSSLOnConnect(SSL_FLAG);
//            email.setFrom(fromAddress);
//            email.setSubject(subject);
//            email.setMsg(message);
//            email.addTo(toAddress);
//            email.send();
//        } catch (Exception ex) {
//            System.out.println("Unable to send email");
//            System.out.println(ex);
//        }
//    }
//        Email from = new Email("test@example.com");
//        String subject = "Sending with SendGrid is Fun";
//        Email to = new Email("test@example.com");
//        Content content = new Content("text/plain", "and easy to do anywhere, even with Java");
//        Mail mail = new Mail(from, subject, to, content);
//
//        SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
//        com.Request request = new Request();
//        try {
//            request.setMethod(Method.POST);
//            request.setEndpoint("mail/send");
//            request.setBody(mail.build());
//            Response response = sg.api(request);
//            System.out.println(response.getStatusCode());
//            System.out.println(response.getBody());
//            System.out.println(response.getHeaders());
//        } catch (IOException ex) {
//            throw ex;
//        }

    }
}
