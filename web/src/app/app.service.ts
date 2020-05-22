import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

export const API_HOST = `http://localhost:8080`;

@Injectable({
  providedIn: 'root'
})
export class AppService {

  constructor(private http: HttpClient) {
  }

  getCustomer() {
    return this.http.get(`${API_HOST}/customers`);
  }

  getSubscribedServicesByCustomerId(customerId: string) {
    return this.http.get(`${API_HOST}/customers/${customerId}/services`);
  }

  getUnsubscribedServicesByCustomerId(customerId: string) {
    return this.http.get(`${API_HOST}/customers/${customerId}/unsubscribed-services`);
  }

  subscribeService(customerId: string, serviceId: string) {
    return this.http.post(`${API_HOST}/customers/${customerId}/services/${serviceId}`, null);
  }

  unsubscribeService(customerId: string, serviceId: string) {
    return this.http.delete(`${API_HOST}/customers/${customerId}/services/${serviceId}`);
  }
}
