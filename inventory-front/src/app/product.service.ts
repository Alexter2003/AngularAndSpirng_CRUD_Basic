import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from './product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private url = "http://localhost:8080/api/inventory/products";
  private clientHttp = inject(HttpClient);

  getProducts(): Observable<Product[]>{
    return this.clientHttp.get<Product[]>(this.url);
  }

  addProduct(product: Product): Observable<Object>{
    return this.clientHttp.post(this.url, product);
  }

  getProductById(id: number): Observable<Product>{
    return this.clientHttp.get<Product>(`${this.url}/${id}`);
  }

  updateProduct(id: number, product: Product): Observable<Object>{
    return this.clientHttp.put(`${this.url}/${id}`, product);
  }

  deleteProduct(id: number): Observable<Object>{
    return this.clientHttp.delete(`${this.url}/${id}`)
  }

  constructor() { }
}
