import { Routes } from '@angular/router';
import { ProductListComponent } from './product-list/product-list.component';
import { AddProductComponent } from './add-product/add-product.component';


export const routes: Routes = [
  {
    path: 'products', component: ProductListComponent
  },
  {
    path: '', redirectTo: 'products', pathMatch: 'full'
  },
  {
    path: 'add-product', component: AddProductComponent
  }
];
