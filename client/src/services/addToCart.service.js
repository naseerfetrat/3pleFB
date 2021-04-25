import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8080/cart';

class ItemService {
    index() {
      return axios.get(API_URL);
    }
  
    addCartItem(data , iid , qty ) {
      return axios.post(API_URL + `/add/${iid}/${qty}`, data , { headers: authHeader().Authorization });
    }
  
    updateCartItem(item , iid , qty) {
      return axios.put(API_URL + `/update/${iid}/${qty}`, { headers: authHeader().Authorization } , item);
    }
  
    deleteCartItem(iid) {
      return axios.delete(API_URL + `/${iid}/delete`, { headers: authHeader().Authorization });
    }
  }
  
  export default new ItemService();
  