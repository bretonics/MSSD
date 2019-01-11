import { Injectable } from '@angular/core';

import { Contact } from './contact';
import { CONTACTS } from './mock-data';

@Injectable()
export class AddressProviderService {

  constructor() { }

  getFriends(): Contact[] {
  	return CONTACTS;
  }

  getFriend(id: number): Contact {
  	let friends:Contact[] = this.getFriends();
    let friend: Contact = friends.find(
    		f => {return (f.id == id)});
    return friend;
  }

  addFriend(): Contact {
  	let friends: Contact[] = this.getFriends();
  	let maxId: number;

  	if (friends && friends.length > 0) {
  		maxId = friends[friends.length - 1].id;
  	} else {
  		maxId = 0;
  	}

  	let friend: Contact = new Contact();
  	friend.id = maxId + 1;
  	friends.push(friend);
  	return friend;
  }

  saveContact(contact: Contact): void {
    let contacts: Contact[] = this.getFriends();
    let target: Contact =
      contacts.find(c => {return (c.id == contact.id)});
    if (!target) {
    	contacts.push(contact);
    } else {
      Object.assign(target, contact);
    }
  }

  deleteContact(contact: Contact) {

    if(confirm("Are you sure to delete " + contact.name)) {
      console.log("Deleting");
      let contacts: Contact[] = this.getFriends();
      let target: Contact = contacts.find(c => {return (c.id == contact.id)});
      const index: number = contacts.indexOf(contact);
      if (index !== -1) {
        contacts.splice(index, 1);
      }
    }

  }

}
