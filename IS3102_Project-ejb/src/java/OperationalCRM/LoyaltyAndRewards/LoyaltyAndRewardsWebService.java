package OperationalCRM.LoyaltyAndRewards;

import EntityManager.LineItemEntity;
import EntityManager.MemberEntity;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(serviceName = "LoyaltyAndRewardsWebService")
@Stateless
public class LoyaltyAndRewardsWebService {

    @EJB
    LoyaltyAndRewardsBeanLocal LoyaltyAndRewardsBeanLocal;

    @WebMethod
    public Integer getMemberLoyaltyPoints(@WebParam(name = "memberEmail") String memberEmail) {
        return LoyaltyAndRewardsBeanLocal.getMemberLoyaltyPointsAmount(memberEmail);
    }

    @WebMethod
    public MemberEntity getMemberViaEmail(@WebParam(name = "memberEmail") String memberEmail) {
        return LoyaltyAndRewardsBeanLocal.getMemberViaEmail(memberEmail);
    }

    @WebMethod
    public MemberEntity getMemberViaCard(@WebParam(name = "memberCard") String memberCard) {
        return LoyaltyAndRewardsBeanLocal.getMemberViaCard(memberCard);
    }

    @WebMethod
    public Boolean createSyncWithPhoneRequest(@WebParam(name = "qrCode") String qrCode) {
        return LoyaltyAndRewardsBeanLocal.createSyncWithPhoneRequest(qrCode);
    }

    @WebMethod
    public String getSyncWithPhoneStatus(@WebParam(name = "qrCode") String qrCode) {
        return LoyaltyAndRewardsBeanLocal.getSyncWithPhoneStatus(qrCode);
    }

    @WebMethod
    public List<LineItemEntity> getMemberShoppingList(@WebParam(name = "email") String email) {
        return LoyaltyAndRewardsBeanLocal.getMemberShoppingList(email);
    }

    @WebMethod
    public Boolean tieMemberToSyncRequest(@WebParam(name = "email") String email, @WebParam(name = "qrCode") String qrCode) {
        return LoyaltyAndRewardsBeanLocal.tieMemberToSyncRequest(email, qrCode);
    }
}
