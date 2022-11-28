import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { Route, extract } from '@app/core';
import { DecretiComponent } from './decreti.component';

const routes: Routes = [
  Route.withShell([
    { path: 'decreti', component: DecretiComponent, data: { title: extract('decrees') } }
  ])
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
  providers: []
})
export class DecretiRoutingModule { }
