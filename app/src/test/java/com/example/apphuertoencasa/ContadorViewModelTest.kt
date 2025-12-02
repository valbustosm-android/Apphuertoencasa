package com.example.apphuertoencasa.ui.theme.viewModel

import com.example.apphuertoencasa.ui.theme.model.Fruta
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ContadorViewModelTest {

    private lateinit var viewModel: ContadorViewModel

    @Before
    fun setup() {
        viewModel = ContadorViewModel()
    }

    @Test
    fun `aumentarContador incrementa contador cuando hay stock disponible`() {
        val fruta = Fruta(
            id = "1",
            description = "Manzana",
            imageUrl = "",
            stock = 5,
            contador = 0
        )
        viewModel.setFrutasList(listOf(fruta))

        viewModel.aumentarContador(fruta)

        val result = viewModel.frutas.value.first() // toma la fruta de esa lista
        assertEquals(1, result.contador)
    }

    @Test
    fun `aumentarContador NO incrementa cuando contador es igual al stock`() {
        val fruta = Fruta(
            id = "1",
            description = "Manzana",
            imageUrl = "",
            stock = 3,
            contador = 3
        )
        viewModel.setFrutasList(listOf(fruta))

        viewModel.aumentarContador(fruta)

        val result = viewModel.frutas.value.first()
        assertEquals(3, result.contador)
    }

    @Test
    fun `disminuirContador decrementa contador cuando es mayor que cero`() {
        // El contador el mayor a cero por eso disminuye
        val fruta = Fruta(
            id = "1",
            description = "Manzana",
            imageUrl = "",
            stock = 5,
            contador = 2
        )
        viewModel.setFrutasList(listOf(fruta))

        viewModel.disminuirContador(fruta)

        val result = viewModel.frutas.value.first()
        assertEquals(1, result.contador)
    }

    @Test
    fun `disminuirContador NO decrementa cuando contador es cero`() {
        // al disminuir va a quedar en 0, no en -1
        val fruta = Fruta(
            id = "1",
            description = "Manzana",
            imageUrl = "",
            stock = 5,
            contador = 0
        )
        viewModel.setFrutasList(listOf(fruta))

        viewModel.disminuirContador(fruta)

        val result = viewModel.frutas.value.first()
        assertEquals(0, result.contador)
    }

    @Test
    fun `aumentarContador solo afecta a la fruta correcta`() {
        val fruta1 = Fruta("1", "Manzana", "", stock = 5, contador = 0)
        val fruta2 = Fruta("2", "Pera", "", stock = 5, contador = 0)
        viewModel.setFrutasList(listOf(fruta1, fruta2))

        viewModel.aumentarContador(fruta1)

        val result1 = viewModel.frutas.value[0]
        val result2 = viewModel.frutas.value[1]

        assertEquals(1, result1.contador)
        assertEquals(0, result2.contador)
    }
}
