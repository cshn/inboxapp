package io.javabrains.inbox.controllers;

import io.javabrains.inbox.creditcard.CreditCard;
import io.javabrains.inbox.creditcard.CreditCardRepository;
import io.javabrains.inbox.creditcard.CreditCardService;
import io.javabrains.inbox.emaillist.EmailListItem;
import io.javabrains.inbox.emaillist.EmailListItemRepository;
import io.javabrains.inbox.folders.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class InboxController {
    @Autowired
    private FolderRepository folderRepository;
    @Autowired
    private FolderService folderService;
    @Autowired
    private CreditCardService creditCardService;
    @Autowired
    private EmailListItemRepository emailListItemRepository;
    @Autowired
    private CreditCardRepository creditCardRepository;

    @GetMapping(value = "/")
    public String homePage(@RequestParam(required=false) String folder, @AuthenticationPrincipal OAuth2User principal,
                           Model model) {

        if (principal == null || !StringUtils.hasText(principal.getAttribute("login"))) {
            return "index";
        }

        String userId = principal.getAttribute("login");
        List<Folder> defaultFolders = folderService.fetchDefaultFolders(userId);
        model.addAttribute("defaultFolders", defaultFolders);
        model.addAttribute("stats", folderService.mapCountToLabels(userId));
        model.addAttribute("userName", principal.getAttribute("name"));

        if(!StringUtils.hasText(folder)) {
            folder = "Inbox";
        }
        List<EmailListItem> emailList = emailListItemRepository.findAllByKey_IdAndKey_Label(userId, folder);
        model.addAttribute("emailList", emailList);
        model.addAttribute("folderName", folder);

        creditCardService.createDefaultCreditCards(userId);
        List<CreditCard> cardList = creditCardRepository.findAllById(userId);
        model.addAttribute("cardList", cardList);

        return "index-page";
    }
}
