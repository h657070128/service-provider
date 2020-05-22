import { Component, OnInit } from '@angular/core';
import { AppService } from './app.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  customers = [];
  currentCustomer = null;
  subscribedServices = [];
  unsubscribedServices = [];

  constructor(
    private appService: AppService
    ) {
  }

  ngOnInit() {
    this.appService.getCustomer().subscribe((customers: any[]) => {
      this.customers = customers;
      if (this.customers && this.customers.length) {
        this.currentCustomer = this.customers[0];
        this.getSubscribedServicesByCustomerId(this.currentCustomer.id);
        this.getUnsubscribedServicesByCustomerId(this.currentCustomer.id);
      }
    });
  }

  getSubscribedServicesByCustomerId = (customerId: string) => {
    this.appService.getSubscribedServicesByCustomerId(customerId).subscribe((services: any[]) => {
      this.subscribedServices = services;
    });
  }

  getUnsubscribedServicesByCustomerId = (customerId: string) => {
    this.appService.getUnsubscribedServicesByCustomerId(customerId).subscribe((services: any[]) => {
      this.unsubscribedServices = services;
    });
  }

  currentCustomerOnChange = (selectedCustomer) => {
    this.getSubscribedServicesByCustomerId(selectedCustomer.id);
    this.getUnsubscribedServicesByCustomerId(selectedCustomer.id);
  }

  unsubscribe = (serviceId: string) => {
    this.appService.unsubscribeService(this.currentCustomer.id, serviceId).subscribe(() => {
      this.getSubscribedServicesByCustomerId(this.currentCustomer.id);
      this.getUnsubscribedServicesByCustomerId(this.currentCustomer.id);
    });
  }

  subscribe = (serviceId: string) => {
    this.appService.subscribeService(this.currentCustomer.id, serviceId).subscribe(() => {
      this.getSubscribedServicesByCustomerId(this.currentCustomer.id);
      this.getUnsubscribedServicesByCustomerId(this.currentCustomer.id);
    });
  }
}
