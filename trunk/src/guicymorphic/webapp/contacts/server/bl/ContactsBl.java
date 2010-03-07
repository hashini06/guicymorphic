package guicymorphic.webapp.contacts.server.bl;

import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.wideplay.warp.persist.TransactionType;
import com.wideplay.warp.persist.Transactional;
import guicymorphic.webapp.contacts.server.dal.ContactDao;
import guicymorphic.webapp.contacts.server.entity.ContactEntity;
import guicymorphic.webapp.contacts.shared.dto.ContactDisplayDto;
import guicymorphic.webapp.contacts.shared.dto.ContactDto;
import guicymorphic.webapp.contacts.shared.rpc.ContactsService;
import org.dozer.Mapper;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by IntelliJ IDEA. User: alen Date: Feb 27, 2010 Time: 6:29:46 PM To change this template
 * use File | Settings | File Templates.
 */
@Singleton
public class ContactsBl implements ContactsService {

    private final ContactDao dao;
    private final Mapper dozer;

    @Inject
    public ContactsBl(ContactDao dao, Mapper dozer) {
        this.dao = dao;
        this.dozer = dozer;
    }

    @Transactional
    public ContactDto addOrUpdateContact(ContactDto contact) {
        Preconditions.checkNotNull(contact);

        // map contact dto -> contact entity
        ContactEntity entity = dozer.map(contact, ContactEntity.class);

        dao.saveOrUpdate(entity);

        // map back entity to dto
        // maybe going over board a little;)
        return dozer.map(entity, ContactDto.class);
    }

    @Transactional
    public Boolean deleteContact(String id) {
        Preconditions.checkNotNull(id);
        try {
            dao.deleteById(Long.valueOf(id));
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }

    // Must NOT BE @Transactional SEE BELLOW

    public ArrayList<ContactDisplayDto> deleteContacts(ArrayList<String> ids) {
        Preconditions.checkNotNull(ids);
        // deletes in it's own transaction
        deleteInTxn(ids);
        // after the call returns successfully we are guaranteed changes were made to db

        // a read only transaction is started around getContactDetails
        // it is an optimization for hibernate
        // use only when you know no modification will be done
        ArrayList<ContactDisplayDto> details = getContactDetails();
        // we are guaranteed to see the changes
        // note if we would make this method i.e. deleteContacts @Transactional
        // then we would not be guaranteed see the changes made by the deletes
        // as transaction commit is done after the method returns
        return details;
    }

    @Transactional
    public void deleteInTxn(ArrayList<String> ids) {
        for (String id : ids) {
            dao.deleteById(Long.valueOf(id));
        }
    }

    @Transactional(type = TransactionType.READ_ONLY)
    public ArrayList<ContactDisplayDto> getContactDetails() {
        Collection<ContactEntity> contacts = dao.findAll();

        ArrayList<ContactDisplayDto> dtos = new ArrayList<ContactDisplayDto>();

        for (ContactEntity entity : contacts) {
            dtos.add(dozer.map(entity, ContactDisplayDto.class));
        }

        return dtos;
    }

    @Transactional(type = TransactionType.READ_ONLY)
    public ContactDto getContact(String id) {
        Preconditions.checkNotNull(id);
        ContactEntity entity = dao.getById(Long.valueOf(id));
        // dozer fails if null on mapping
        if (entity == null) {
            return null;
        }
        return dozer.map(entity, ContactDto.class);
    }


}
