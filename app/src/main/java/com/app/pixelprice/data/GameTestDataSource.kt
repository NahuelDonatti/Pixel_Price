package com.app.pixelprice.data

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.delay

class GameTestDataSource : IGameDataSource {
   override suspend fun getGameList(search: String) : List<Game>{

       delay(5000)
       val gson = Gson()
       val listType = object : TypeToken<List<Game>>(){}.type
       return gson.fromJson(json, listType)

    }

   private var json = """
        [
  {
    "gameID": "612",
    "steamAppID": null,
    "cheapest": "2.31",
    "cheapestDealID": "tyTH88J0PXRvYALBjV3cNHd5Juq1qKcu4tG4lBiUCt4%3D",
    "external": "LEGO Batman",
    "internalName": "LEGOBATMAN",
    "thumb": "https://cdn.fanatical.com/production/product/400x225/105f34ca-7757-47ad-953e-7df7f016741e.jpeg"
  },
  {
    "gameID": "167613",
    "steamAppID": null,
    "cheapest": "3.09",
    "cheapestDealID": "0r0ce2sASn%2BPnDjouUO2QY7dIcPzWF1bXeV1espcrGw%3D",
    "external": "LEGO Batman 2",
    "internalName": "LEGOBATMAN2",
    "thumb": "https://cdn.fanatical.com/production/product/400x225/4cf0701e-77bf-4539-bda7-129ab3e81f8b.jpeg"
  },
  {
    "gameID": "167910",
    "steamAppID": "502820",
    "cheapest": "3.87",
    "cheapestDealID": "duswFlPNu%2B0QtHl5kEcEA%2BVFeNFm2CAuPOkaTx6nS3g%3D",
    "external": "Batman: Arkham VR",
    "internalName": "BATMANARKHAMVR",
    "thumb": "https://shared.fastly.steamstatic.com/store_item_assets/steam/apps/502820/capsule_sm_120.jpg?t=1684483128"
  },
  {
    "gameID": "136751",
    "steamAppID": "53403",
    "cheapest": "4.99",
    "cheapestDealID": "tWdHYhBxTi%2Boen9dGX6I0I%2BfklTJOHB0SykaLcbpsqk%3D",
    "external": "LEGO Batman Trilogy",
    "internalName": "LEGOBATMANTRILOGY",
    "thumb": "https://shared.fastly.steamstatic.com/store_item_assets/steam/subs/53403/capsule_sm_120.jpg?t=1731534809"
  },
  {
    "gameID": "107598",
    "steamAppID": "208650",
    "cheapest": "3.09",
    "cheapestDealID": "6ooKBSs5XsqUPxaLR4k9S1kG7w7Icd%2F8Ig0%2BliKLfmU%3D",
    "external": "Batman: Arkham Knight",
    "internalName": "BATMANARKHAMKNIGHT",
    "thumb": "https://shared.fastly.steamstatic.com/store_item_assets/steam/apps/208650/capsule_sm_120.jpg?t=1745534372"
  },
  {
    "gameID": "97941",
    "steamAppID": "209000",
    "cheapest": "3.29",
    "cheapestDealID": "pQMnR6l11XoFIKdRtMxeu8MIadxcPenVBH%2BlaQIQmc8%3D",
    "external": "Batman: Arkham Origins",
    "internalName": "BATMANARKHAMORIGINS",
    "thumb": "https://shared.fastly.steamstatic.com/store_item_assets/steam/apps/209000/capsule_sm_120.jpg?t=1746562474"
  },
  {
    "gameID": "198190",
    "steamAppID": null,
    "cheapest": "15.99",
    "cheapestDealID": "DnVEZriqpYWyJoC4CN2%2Fe07Su8RZ61DATyjmfNk%2FwKI%3D",
    "external": "Batman Arkham City GOTY",
    "internalName": "BATMANARKHAMCITYGOTY",
    "thumb": "https://us.gamesplanet.com/acache/15/46/1/us/packshot-92b3116edd0584b61a131fa716d0a766.jpg"
  },
  {
    "gameID": "198317",
    "steamAppID": "320795",
    "cheapest": "6.99",
    "cheapestDealID": "gMIYG%2BOxGg6YDudPepsbZSz3QCnSv0z9xRKSxyDsHQ4%3D",
    "external": "Batman: Arkham Collection",
    "internalName": "BATMANARKHAMCOLLECTION",
    "thumb": "https://shared.fastly.steamstatic.com/store_item_assets/steam/subs/320795/capsule_sm_120.jpg?t=1745533717"
  },
  {
    "gameID": "2425",
    "steamAppID": "21000",
    "cheapest": "2.99",
    "cheapestDealID": "%2BO6Wtr7wWetChHL4HnewWiTKf%2FgeWgJ9XejXTatD7yA%3D",
    "external": "LEGO Batman: The Videogame",
    "internalName": "LEGOBATMANTHEVIDEOGAME",
    "thumb": "https://shared.fastly.steamstatic.com/store_item_assets/steam/apps/21000/capsule_sm_120.jpg?t=1745963205"
  },
  {
    "gameID": "126255",
    "steamAppID": "313690",
    "cheapest": "3.87",
    "cheapestDealID": "GIen44MQlervWwNXw1e1k2ydTlgX8fzBHFbRgWKUGSs%3D",
    "external": "LEGO Batman 3: Beyond Gotham",
    "internalName": "LEGOBATMAN3BEYONDGOTHAM",
    "thumb": "https://shared.fastly.steamstatic.com/store_item_assets/steam/apps/313690/capsule_sm_120.jpg?t=1745962284"
  },
  {
    "gameID": "199261",
    "steamAppID": null,
    "cheapest": "39.98",
    "cheapestDealID": "vVlFdl8%2BVYZuEwKlNPy7iUrK49IXdqtNSKDgTrhw4sM%3D",
    "external": "Batman Game of the Year Pack",
    "internalName": "BATMANGAMEOFTHEYEARPACK",
    "thumb": "https://images.greenmangaming.com/9c282eceace145df99f76f0d08febdfe/1fbba96f5f8a4ffbb135e320bcc2346c.jpg"
  },
  {
    "gameID": "155074",
    "steamAppID": "498240",
    "cheapest": "6.13",
    "cheapestDealID": "CcBv6qrQ50LT4myqn4cH%2FGNenGHtCsF%2Bpv%2BiD%2BQ3tR4%3D",
    "external": "Batman - The Telltale Series",
    "internalName": "BATMANTHETELLTALESERIES",
    "thumb": "https://shared.fastly.steamstatic.com/store_item_assets/steam/apps/498240/capsule_sm_120.jpg?t=1733944832"
  },
  {
    "gameID": "88269",
    "steamAppID": "213330",
    "cheapest": "3.99",
    "cheapestDealID": "WkZYFLKPzkq7i7hiZ87z%2FCXEUQn%2Ba1JqJwQO2Nn5UKI%3D",
    "external": "LEGO Batman 2: DC Super Heroes",
    "internalName": "LEGOBATMAN2DCSUPERHEROES",
    "thumb": "https://shared.fastly.steamstatic.com/store_item_assets/steam/apps/213330/capsule_sm_120.jpg?t=1745962914"
  },
  {
    "gameID": "262248",
    "steamAppID": null,
    "cheapest": "14.99",
    "cheapestDealID": "OKARgK1wBMW6W6Mod6tfJ%2FS582x6mmaZzcsrhKNN9zw%3D",
    "external": "HOT WHEELS - Batman Expansion",
    "internalName": "HOTWHEELSBATMANEXPANSION",
    "thumb": "https://cdn1.epicgames.com/salesEvent/salesEvent/EGS_HOTWHEELSBatmanExpansion_MilestoneSrl_DLC_S2_1200x1600-eb509c7f016c85c206fb702fe7483ebf"
  },
  {
    "gameID": "214905",
    "steamAppID": null,
    "cheapest": "4.99",
    "cheapestDealID": "OTJxScRuO1D3hoLSFDjrWKZKm%2FrcXvSACoDDGijZjrY%3D",
    "external": "Telltale Batman Shadows Mode",
    "internalName": "TELLTALEBATMANSHADOWSMODE",
    "thumb": "https://images.gog-statics.com/e60c4596e9f9a74ca587efcb561b94d0e61e80648bd478a7e74ec8f6cd93c44a_product_tile_117h.webp"
  },
  {
    "gameID": "89107",
    "steamAppID": null,
    "cheapest": "3.99",
    "cheapestDealID": "yPE8bI5eH%2FFwI0L7KaEUTfOzYmWLPj%2F76DHrJEnFWeY%3D",
    "external": "Batman: Arkham City GOTY Edition",
    "internalName": "BATMANARKHAMCITYGOTYEDITION",
    "thumb": "https://hb.imgix.net/08abd0c1b25f2ffc652ae7d6537443cee7eccf94.jpg?auto=compress,format&fit=crop&h=84&w=135&s=6ae6339224425ee6e7b7e48ddea68e1d"
  },
  {
    "gameID": "143771",
    "steamAppID": null,
    "cheapest": "3.87",
    "cheapestDealID": "69tZA0nu%2Bbnm7IcOTfAwd4ZXEaXX5hYEmPQjf%2FjL0Uc%3D",
    "external": "Batman: Arkham Knight - Season Pass",
    "internalName": "BATMANARKHAMKNIGHTSEASONPASS",
    "thumb": "https://sttc.gamersgate.com/images/product/batmantm-arkham-knight-season-pass/cover-180-5d9655.jpg"
  },
  {
    "gameID": "214906",
    "steamAppID": null,
    "cheapest": "29.96",
    "cheapestDealID": "A3hjhaYvTj0ZcYKpdslQ4PASVGEwe0Zwfrd61gkWLXE%3D",
    "external": "Telltale Batman Shadows Edition",
    "internalName": "TELLTALEBATMANSHADOWSEDITION",
    "thumb": "https://images.greenmangaming.com/bb1f86edd3be41e3bb8948c00523e5ef/bd6b538c1a7b4f138b862d3c825ff005.jpg"
  },
  {
    "gameID": "87778",
    "steamAppID": null,
    "cheapest": "3.99",
    "cheapestDealID": "aMEpZwGFx7UiJqluONoLp2zqFVGwxfu9OEG79IaqwVE%3D",
    "external": "Batman Arkham Asylum GOTY Edition",
    "internalName": "BATMANARKHAMASYLUMGOTYEDITION",
    "thumb": "https://hb.imgix.net/2a07e9e90c9af53c5120151c9ff4e0aa3771ce99.jpeg?auto=compress,format&fit=crop&h=84&w=135&s=0f42069b4949098d7d589d28a9f66311"
  },
  {
    "gameID": "246183",
    "steamAppID": null,
    "cheapest": "19.99",
    "cheapestDealID": "3VlwNpcd%2FDMETYgzpXd1WDzLWBTn9qHFBhtkSUbnCMs%3D",
    "external": "Batman Arkham City: Game of the Year",
    "internalName": "BATMANARKHAMCITYGAMEOFTHEYEAR",
    "thumb": "https://images.greenmangaming.com/3e529338224949f280276fa6a5b75fec/933b9d36ea2d49b4b389899814729378.jpg"
  },
  {
    "gameID": "102510",
    "steamAppID": null,
    "cheapest": "4.09",
    "cheapestDealID": "%2FAlbyHHukrRjGvgYmAjJZsCODtzg6znE2UF5oiF0iFY%3D",
    "external": "Batman Arkham Origins Season Pass",
    "internalName": "BATMANARKHAMORIGINSSEASONPASS",
    "thumb": "https://images.greenmangaming.com/1c43fd1ff43744c08e854d6ff963c91d/2c8b09a294f4401ab3747b99b6d828f1.jpg"
  },
  {
    "gameID": "246576",
    "steamAppID": null,
    "cheapest": "14.99",
    "cheapestDealID": "Fi5iMIssmS3PtiA3m42zXTR22B9GvIcr2NE8Csb5kFw%3D",
    "external": "The Telltale Batman Shadows Edition",
    "internalName": "THETELLTALEBATMANSHADOWSEDITION",
    "thumb": "https://cdn1.epicgames.com/offer/f2bfff793b224f6190a394f461c9a4b8/s2_510x680-24368ffb56983936896f0a7d908667ec"
  },
  {
    "gameID": "225758",
    "steamAppID": null,
    "cheapest": "4.99",
    "cheapestDealID": "PUPfxJiMyt4dbcslwvOVad0v%2BgtknEhy8c90qOpVp9w%3D",
    "external": "Telltale Batman Shadows Mode Bundle",
    "internalName": "TELLTALEBATMANSHADOWSMODEBUNDLE",
    "thumb": "https://hb.imgix.net/bcd6694f5442c730d3b16bdb07e956e52fe17b32.png?auto=compress,format&fit=crop&h=84&w=135&s=748daa2aa04096c42a24db2baf8320ff"
  },
  {
    "gameID": "250727",
    "steamAppID": null,
    "cheapest": "19.99",
    "cheapestDealID": "WT%2FMFjw1bIOhh5gs6slTqvwUdrGR2W4wEwyYGlT1G98%3D",
    "external": "Batman: Arkham Asylum Game of the Year",
    "internalName": "BATMANARKHAMASYLUMGAMEOFTHEYEAR",
    "thumb": "https://images.greenmangaming.com/0c2b457baa0a4e778c31771ead715300/88c9e725b8ff4610a2e1970e0f4a08f1.jpg"
  },
  {
    "gameID": "214907",
    "steamAppID": null,
    "cheapest": "4.99",
    "cheapestDealID": "lBW7zyO4rDYgP9peypdBSZC%2B6dp8bUJMtwXlUVt9ghY%3D",
    "external": "Batman Shadows Mode: The Enemy Within",
    "internalName": "BATMANSHADOWSMODETHEENEMYWITHIN",
    "thumb": "https://images.gog-statics.com/70d653226ede85bb70a8d4022a2d02b6c2e2bb9775e87b6a9ca5c30d6ac7f815_product_tile_117h.webp"
  },
  {
    "gameID": "221906",
    "steamAppID": null,
    "cheapest": "4.99",
    "cheapestDealID": "E7P6Oz50WDEWzQZZjj8b1l6e7C3S0sr43hOQnoDCtqk%3D",
    "external": "Batman - The Enemy Within Shadows Mode",
    "internalName": "BATMANTHEENEMYWITHINSHADOWSMODE",
    "thumb": "https://images.greenmangaming.com/85ad7081bac34a3cb4151daeb6540b50/9e93071c54604454974132c72bab146f.jpg"
  },
  {
    "gameID": "108605",
    "steamAppID": null,
    "cheapest": "8.49",
    "cheapestDealID": "1kwCcJiqxMJJyas58G3FQ4Od%2BbujQrxSF19FRSAmcPE%3D",
    "external": "Batman: Arkham Origins - Cold, Cold Heart",
    "internalName": "BATMANARKHAMORIGINSCOLDCOLDHEART",
    "thumb": "https://images.greenmangaming.com/ba8d4cd6e14e40fca132f346e2d0d3b4/44f1b54092f3498f88b3d7c1df88451b.jpg"
  },
  {
    "gameID": "143817",
    "steamAppID": null,
    "cheapest": "4.65",
    "cheapestDealID": "jmXx3skg%2FdKIZzSGTEVFUxE3th1f0%2BPbNlP96is7CqM%3D",
    "external": "Batman: Arkham Knight - Premium Edition",
    "internalName": "BATMANARKHAMKNIGHTPREMIUMEDITION",
    "thumb": "https://images.greenmangaming.com/b47c3b1bc8a5468ab3ada8832be8355c/4de3b472f988463abf6a869bcdda44d7.jpg"
  },
  {
    "gameID": "136749",
    "steamAppID": null,
    "cheapest": "14.99",
    "cheapestDealID": "8BqlIyNqg3zId7BJh5UdsLpD4%2FEAY9Pbcmc87JTtsd8%3D",
    "external": "LEGO Batman 3: Beyond Gotham Season Pass",
    "internalName": "LEGOBATMAN3BEYONDGOTHAMSEASONPASS",
    "thumb": "https://sttc.gamersgate.com/images/product/lego-batman-3-beyond-gotham-season-pass/cover-180-e8ad17.jpg"
  },
  {
    "gameID": "221904",
    "steamAppID": null,
    "cheapest": "4.99",
    "cheapestDealID": "oWzY%2BWDZExyELDQrL4xy3kd6P1p%2BOQMQ%2FIOeMaN0QaU%3D",
    "external": "Batman - The Telltale Series Shadows Mode",
    "internalName": "BATMANTHETELLTALESERIESSHADOWSMODE",
    "thumb": "https://images.greenmangaming.com/9cc80f2eb57e4588b159b818fed76288/7afd0ac5cde2472b8fde02a4720dc0ad.jpg"
  },
  {
    "gameID": "89095",
    "steamAppID": "200260",
    "cheapest": "3.29",
    "cheapestDealID": "rIYxhdpFe8%2BeO7wLxw2LUnGNwahew3syV%2ByGcE95KHk%3D",
    "external": "Batman Arkham City Game of the Year Edition",
    "internalName": "BATMANARKHAMCITYGAMEOFTHEYEAREDITION",
    "thumb": "https://shared.fastly.steamstatic.com/store_item_assets/steam/apps/200260/capsule_sm_120.jpg?t=1745964057"
  },
  {
    "gameID": "178376",
    "steamAppID": null,
    "cheapest": "5.24",
    "cheapestDealID": "pTeQKzHuRK4TDqHYLY4xolVHA5s%2BVlwXQrlqzqnLCks%3D",
    "external": "LEGO Batman 3: Beyond Gotham Premium Edition",
    "internalName": "LEGOBATMAN3BEYONDGOTHAMPREMIUMEDITION",
    "thumb": "https://images.gog-statics.com/0d81cdadc840148cbd6e1f1a6897bdf9d38994b3045c7a394f8426a82775b9f4_product_tile_117h.webp"
  },
  {
    "gameID": "171102",
    "steamAppID": "675260",
    "cheapest": "6.14",
    "cheapestDealID": "KAdDRCcmbXTBz9KKu4AYT3yfiKG0KKMV%2FH2RuC0RRdg%3D",
    "external": "Batman: The Enemy Within - The Telltale Series",
    "internalName": "BATMANTHEENEMYWITHINTHETELLTALESERIES",
    "thumb": "https://shared.fastly.steamstatic.com/store_item_assets/steam/apps/675260/capsule_sm_120.jpg?t=1734105400"
  },
  {
    "gameID": "146",
    "steamAppID": "35140",
    "cheapest": "3.29",
    "cheapestDealID": "od8ovZIEQ6Tzfd5YvkZZQ6B6F6Dfft%2B%2Fhn99oTCje%2Bw%3D",
    "external": "Batman: Arkham Asylum Game of the Year Edition",
    "internalName": "BATMANARKHAMASYLUMGAMEOFTHEYEAREDITION",
    "thumb": "https://shared.fastly.steamstatic.com/store_item_assets/steam/apps/35140/capsule_sm_120.jpg?t=1745966724"
  },
  {
    "gameID": "107869",
    "steamAppID": "267490",
    "cheapest": "3.29",
    "cheapestDealID": "KqtHzQn65YijjFXXurIskyOm78W16wl2KOFlNZU%2Faq4%3D",
    "external": "Batman: Arkham Origins Blackgate - Deluxe Edition",
    "internalName": "BATMANARKHAMORIGINSBLACKGATEDELUXEEDITION",
    "thumb": "https://shared.fastly.steamstatic.com/store_item_assets/steam/apps/267490/capsule_sm_120.jpg?t=1726531663"
  },
  {
    "gameID": "250195",
    "steamAppID": null,
    "cheapest": "2.99",
    "cheapestDealID": "A2XgGTglsm6iTyN%2BzxnWqIrErGb1uxYlGt4vUZyeG0k%3D",
    "external": "LEGO DC Super-Villains Batman: The Animated Series Level Pack",
    "internalName": "LEGODCSUPERVILLAINSBATMANTHEANIMATEDSERIESLEVELPACK",
    "thumb": "https://images.gog-statics.com/7b55f49b5d2898986176e45d97391b263cc46b804ce7e8dc81ecb242c72f1e7d_product_tile_117h.webp"
  }
]
        
    """.trimIndent()

}