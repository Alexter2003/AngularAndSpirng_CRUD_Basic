import { Component, inject } from '@angular/core';
import { Product } from '../product';
import { ProductService } from '../product.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-product-list',
  imports: [],
  templateUrl: './product-list.component.html',
})
export class ProductListComponent {
    products!: Product[];

    private productService = inject(ProductService);
    private router = inject(Router);


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

    editProduct(id: number){
      this.router.navigate(['edit-product', id]);
    }

    deleteProduct(id: number){
      this.productService.deleteProduct(id).subscribe({
        next: (data) => this.getProducts(),
        error: (error) => console.log("Error to delete product ", error)
      })
    }
}
