import { Component, inject } from '@angular/core';
import { Product } from '../product';
import { ProductService } from '../product.service';

@Component({
  selector: 'app-product-list',
  imports: [],
  templateUrl: './product-list.component.html',
})
export class ProductListComponent {
    products!: Product[];

    private productService = inject(ProductService);

    ngOnInit() {
      //load products
      this.getProducts();
    }

    private getProducts(): void {
      this.productService.getProducts().subscribe(
        {
          next: (data) => {
            this.products = data;
          },
          error: (error) => {
            console.log("Error to get products ", error)
          }
        }
      );
    }
}
