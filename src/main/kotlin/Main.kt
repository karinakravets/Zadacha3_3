import java.util.*

fun main() {
    val alphabet = arrayOf(
        'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З', 'И', 'Й', 'К',
        'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц',
        'Ч', 'Ш', 'Щ', 'Ь', 'Ы', 'Ъ', 'Э', 'Ю', 'Я'
    )

    val scanner = Scanner(System.`in`)

    println("Введите '1', чтобы зашифровать текст, или '2', чтобы расшифровать текст:")
    val action = scanner.nextInt()

    println("Введите ключевое слово:")
    val keyword = scanner.next().toUpperCase()

    println("Введите текст:")
    val text = scanner.next().toUpperCase()

    val shiftedAlphabet = shiftAlphabet(alphabet, keyword)

    when (action) {
        1 -> {
            val encryptedText = encrypt(text, shiftedAlphabet)
            println("Зашифрованный текст: $encryptedText")
        }
        2 -> {
            val decryptedText = decrypt(text, shiftedAlphabet)
            println("Расшифрованный текст: $decryptedText")
        }
        else -> println("Некорректный ввод")
    }
}

fun shiftAlphabet(alphabet: Array<Char>, keyword: String): Array<Char> {
    val shiftedAlphabet = Array(alphabet.size) { ' ' }

    for (i in alphabet.indices) {
        val newIndex = (i + keyword[i % keyword.length].toInt() - 'А'.toInt()) % alphabet.size
        shiftedAlphabet[newIndex] = alphabet[i]
    }

    return shiftedAlphabet
}

fun encrypt(text: String, shiftedAlphabet: Array<Char>): String {
    val encryptedText = StringBuilder()

    for (char in text) {
        if (char in 'А'..'Я') {
            val index = char - 'А'
            encryptedText.append(shiftedAlphabet[index])
        } else {
            encryptedText.append(char)
        }
    }

    return encryptedText.toString()
}

fun decrypt(text: String, shiftedAlphabet: Array<Char>): String {
    val decryptedText = StringBuilder()

    for (char in text) {
        if (char in 'А'..'Я') {
            val index = shiftedAlphabet.indexOf(char)
            decryptedText.append('А' + index)
        } else {
            decryptedText.append(char)
        }
    }

    return decryptedText.toString()
}