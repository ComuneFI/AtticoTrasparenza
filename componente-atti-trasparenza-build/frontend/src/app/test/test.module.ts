import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { TestComponent } from './test.component';

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [TestComponent],
  exports: [TestComponent],
  providers: [
  ]
  //bootstrap: [TestComponent] // solo nel principale
})
export class TestModule { }
