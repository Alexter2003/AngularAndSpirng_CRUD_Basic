import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Product } from '../product';
import { ProductService } from '../product.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-product',
  imports: [FormsModule],
  templateUrl: './add-product.component.html',
})

export class AddProductComponent {
  product: Product = new Product();

  private productService = inject(ProductService);
  private router = inject(Router);

  onSubmit(){
    this.saveProduct()
  }

  saveProduct(){
    this.productService.addProduct(this.product).subscribe({
      next: (data) => {
        this.navProductList();
      },
      error: (error: any) => {
        console.log(error);
      }
    })
  }

  navProductList(){
    this.router.navigate(['/products'])
  }
}
