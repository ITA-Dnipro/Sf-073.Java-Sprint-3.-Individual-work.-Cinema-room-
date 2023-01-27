package antifraud.domain.service;


import antifraud.domain.model.IP;

import java.util.List;
import java.util.Optional;

public interface IPService {
    Optional<IP> saveSuspiciousAddress(IP address);

    void deleteAddress(String ipAddress);

    List<IP> showIpAddresses();
}