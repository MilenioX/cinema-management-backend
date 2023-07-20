package com.mundox.management.ports.adapters.database.daos

object SnacksDAO {

  def getAllElements: String =
    """select s.id, s.name, st.name, s.quantity, s.price
       |from management.snacks as s
       |inner join management.snack_type as st
       |on s.snack_type_id = st.id""".stripMargin

  def getSnacksById(id: String): String =
    s"""select s.id, st.name, s.quantity, s.price
      |from management.snacks as s
      |inner join management.snack_type as st
      |on s.snack_type_id = st.id
      |where s.id = $id""".stripMargin
}
