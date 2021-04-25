import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8080/items';

class ItemService {
    index() {
      return axios.get(API_URL);
    }
  
    addItem(data) {
      return axios.post(API_URL + '/add', data);
    }
  
    updateItem(item) {
      return axios.put(API_URL + `/${item.id}/update`, { headers: authHeader().Authorization } , item);
    }
  
    deleteItem(itemId) {
      return axios.delete(API_URL + `/${itemId}/delete`, { headers: authHeader() });
    }
  }
  
  export default new ItemService();
  