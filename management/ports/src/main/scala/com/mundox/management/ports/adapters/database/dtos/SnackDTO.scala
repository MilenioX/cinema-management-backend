package com.mundox.management.ports.adapters.database.dtos

case class SnackDTO(
                   id: Int,
                   name: String,
                   snackType: String,
                   quantity: Int,
                   price: Double
                   )