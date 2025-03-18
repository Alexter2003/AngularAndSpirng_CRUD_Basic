import { Component, inject } from '@angular/core';
import { Product } from '../product';
import { ProductService } from '../product.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-edit-product',
  imports: [FormsModule],
  templateUrl: './edit-product.component.html',
})
export class EditProductComponent {
  product: Product = new Product();

  id!: number;

  private productService = inject(ProductService)
  private router = inject(ActivatedRoute)
  private routerNavigate = inject(Router)

  ngOnInit(): void{
    this.id = this.router.snapshot.params['id']

    this.productService.getProductById(this.id).subscribe({
      next: (data) => {
        this.product = data
      },
      error: (errors: any) => {
        console.log(errors)
      }
    });
  }

  onSubmit(){
    this.editProduct()
  }

  editProduct(){
    this.productService.updateProduct(this.id, this.product).subscribe({
      next: (data) => this.navigateToProductList(),
      error: (errors: any) => {
        console.log(errors)
      }
    });
  }

  navigateToProductList(){
    this.routerNavigate.navigate(['/products'])
  }
}
