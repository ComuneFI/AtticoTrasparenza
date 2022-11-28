import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { Route, extract } from '@app/core';
import { OrdinanzeComponent } from './ordinanze.component';

const routes: Routes = [
  Route.withShell([
    { path: 'ordinanze', component: OrdinanzeComponent, data: { title: extract('ordSystems') } }
  ])
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
  providers: []
})
export class OrdinanzeRoutingModule { }
