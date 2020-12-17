package by.nevar.gamefinder.service;

import by.nevar.gamefinder.models.Championship;
import by.nevar.gamefinder.models.Request;
import by.nevar.gamefinder.models.User;
import by.nevar.gamefinder.repository.IChampionshipRepository;
import by.nevar.gamefinder.repository.IRequestRepository;
import by.nevar.gamefinder.service.serviceInterfaces.IMyRequestService;
import by.nevar.gamefinder.service.serviceInterfaces.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RequestService implements IMyRequestService {
    private final IRequestRepository requestRepository;
    private final IUserService userService;
    private final IChampionshipRepository championshipService;

    @Autowired
    public JavaMailSender emailSender;

    public RequestService(IRequestRepository requestRepository, IUserService userService, IChampionshipRepository championshipService) {
        this.requestRepository = requestRepository;
        this.userService = userService;
        this.championshipService = championshipService;
    }

    public List<Request> findAll() {
        var list = requestRepository.findAll();
        log.info("RequestService : find All Request");
        return list;
    }

    public void addRequest(String status, String message, User user, Championship championship) {
        Request request = new Request();
        request.setMessage(message);
        request.setStatus(status);
        request.setUser(user);
        request.setChampionship(championship);
        log.info("RequestService : add Request");
        this.requestRepository.save(request);
    }

    public Request findByUandC(User user, Championship championship) {
        return requestRepository.findAllByUserAndAndChampionship(user, championship).get(0);
    }

    public boolean isExist(User user, Championship championship) {
        return requestRepository.findAllByUserAndAndChampionship(user, championship).size() > 0;
    }

    public void setRequestStatus(String strToParse, boolean isAccept) {
        strToParse = strToParse.substring(1, strToParse.length() - 1);
        String[] list = strToParse.split(" => ");
        User user = userService.findByUsername(list[0]);
        Championship championship = championshipService.findByName(list[1]);
        Request request = findByUandC(user, championship);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("Overworld Cup");

        if (isAccept) {
            request.setStatus("Accepted");
            message.setText("[ " + strToParse + " ]  " + "was accepted by admin");
        } else {
            request.setStatus("Refused");
            message.setText("[ " + strToParse + " ]  " + "was refused by admin");
        }
        if (user.getEmail() != null) {
            Runnable task = () -> {
                message.setTo(user.getEmail());
                this.emailSender.send(message);
            };
            task.run();
        }

        requestRepository.save(request);
    }
}

