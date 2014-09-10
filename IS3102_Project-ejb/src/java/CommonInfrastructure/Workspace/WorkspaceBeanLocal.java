package CommonInfrastructure.Workspace;

import EntityManager.MessageEntity;
import java.util.List;
import javax.ejb.Local;

@Local
public interface WorkspaceBeanLocal {
    //Check staff exists via AccountManagementBean first before doing anything in this bean
    
    public boolean sendMessage(Long senderStaffID, Long receiverStaffID, String message);
    public List<MessageEntity> listAllInboxMessages(Long staffID);
    public List<MessageEntity> listAllSentMessages(Long staffID);
    
    public MessageEntity readInboxMessage(Long staffID, Long messageID); // returns null if staff or message not found
    //Following returns true if operation suceeds
    public boolean deleteInboxMessage(Long staffID, Long messageID);
    public boolean deleteSentMessage(Long staffID, Long messageID);
}