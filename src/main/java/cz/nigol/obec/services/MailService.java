package cz.nigol.obec.services;

import java.util.*;
import cz.nigol.obec.entities.*;

public interface MailService {
    List<MailLog> getAllMailLogs();
    void sendEmail(String to, String subject, String body);
}